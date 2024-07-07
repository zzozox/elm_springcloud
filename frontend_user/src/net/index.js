import axios from "axios";
import {ElMessage} from "element-plus";
import {jwtDecode} from "jwt-decode";
const authItemName = "authorize"
/**
 * 获取请求头，添加Bearer
 * @returns {{Authorization: string}}
 */
const accessHeader = () => {
    return {
        'Authorization': `Bearer ${takeAccessToken()}`
    }
}

/**
 * 定义默认error处理
 * @param error
 */
const defaultError = (error) => {
    console.error(error)
    ElMessage.error('发生了一些错误，请联系管理员')
}

/**
 * 定义默认请求失败
 * @param message
 * @param status
 * @param url
 */
const defaultFailure = (message, status, url) => {
    console.warn(`请求地址: ${url}, 状态码: ${status}, 错误信息: ${message}`)
    ElMessage.warning(message)
}

/**
 * 获取token
 * @returns {*|null}
 */
function takeAccessToken() {
    const str = localStorage.getItem(authItemName) || sessionStorage.getItem(authItemName);
    if (!str) return null;
    const authObj = JSON.parse(str);
    if (new Date(authObj.expire) <= new Date()) {
        deleteAccessToken();
        ElMessage.warning("登录状态已过期，请重新登录！");
        return null;
    }
    return authObj.token;
}

/**
 * 记录token及令牌过期时间
 * @param remember
 * @param token
 * @param expire
 */
function storeAccessToken(remember, token, expire) {
    const authObj = {
        token: token,
        expire: expire
    };
    const str = JSON.stringify(authObj);
    if (remember)
        localStorage.setItem(authItemName, str);
    else
        sessionStorage.setItem(authItemName, str);
}

/**
 * 删除网页缓存的token
 */
function deleteAccessToken() {
    localStorage.removeItem(authItemName);
    sessionStorage.removeItem(authItemName);
}

/**
 * 封装内部post请求
 * @param url
 * @param data
 * @param headers
 * @param success
 * @param failure
 * @param error
 */
function internalPost(url, data, headers, success, failure, error = defaultError) {
    axios.post(url, data, { headers: headers }).then(({ data }) => {
        console.log(`POST Request to ${url} successful. Response:`, data);
        if (data.code === 200)
            success(data.data);
        else
            failure(data.message, data.code, url);
    }).catch(err => {
        console.error(`POST Request to ${url} failed. Error:`, err);
        error(err);
    });
}

/**
 * 封装内部get请求
 * @param url
 * @param headers
 * @param success
 * @param failure
 * @param error
 */
function internalGet(url, headers, success, failure, error = defaultError) {
    axios.get(url, { headers: headers }).then(({ data }) => {
        console.log(`GET Request to ${url} successful. Response:`, data);
        if (data.code === 200)
            success(data.data);
        else
            failure(data.message, data.code, url);
    }).catch(err => {
        console.error(`GET Request to ${url} failed. Error:`, err);
        error(err);
    });
}

/**
 * 登录方法
 * @param username
 * @param password
 * @param remember
 * @param success
 * @param failure
 */
function login(username, password, remember, success, failure = defaultFailure) {
    internalPost('/api/auth/login', {
        username: username,
        password: password
    }, {
        'Content-Type': 'application/json'
    }, (data) => {
        storeAccessToken(remember, data.token, data.expire);
        ElMessage.success(`登录成功，欢迎 ${data.username} 来到我们的系统`);
        success(data);
    }, failure);
}


/**
 * 登出方法
 * @param success
 * @param failure
 */
function logout(success, failure = defaultFailure) {
    post('/api/auth/logout', {}, () => {
        deleteAccessToken();
        ElMessage.success(`退出登录成功，欢迎您再次使用`);
        success();
    }, failure);
}

/**
 * 对外的post
 * @param url
 * @param data
 * @param success
 * @param failure
 */
function post(url, data, success, failure = defaultFailure) {
    internalPost(url, data, accessHeader(), success, failure);
}

/**
 * 对外的get
 * @param url
 * @param success
 * @param failure
 */
function get(url, success, failure = defaultFailure) {
    internalGet(url, accessHeader(), success, failure);
}

/**
 * 验证是否登录
 * @returns {boolean}
 */
function unauthorized() {
    return !takeAccessToken();
}

/**
 * 获取用户信息
 * @returns {JwtPayload}
 */
function getUser() {
    return jwtDecode(takeAccessToken());
}

export { post, get, login, logout, unauthorized, getUser };
