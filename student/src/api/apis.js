import { fetch } from './fetch'
import { store } from '../store'
//增加、获取、删除、修改角色信息，通过所传过来的方法进行相关操作
//delete(删除角色信息) post(增加角色信息) get(获取角色信息) put(修改角色信息)
export function role_information(req_url, req_method, req_data, req_params, req_header) {
    return fetch({
        url: 'http://localhost:8080/sysRoleTable' + req_url,
        method: req_method,
        data: req_data,
        params: req_params,
        headers: req_header
    })
}