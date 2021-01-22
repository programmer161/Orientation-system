import axios from 'axios'

export function fetch(options){
    //使用promise来异步封装数据
    return new Promise((resolve,reject) => {
        const instance = axios.create({
            headers:{
                'Authorization':localStorage.getItem('token')
            },
            //设置超时时间
            timeout:10000
        });
        instance(options)
        .then(res => {
            //过滤浏览器是否请求成功
            if(res.status === 200){  
                //确保进行处理的数据是否正确
                if(res.data.code === 0){   
                    //返回其操作成功后所得到的相关数据
                    resolve(res.data);
                }else{
                    //返回其操作失败后相关的提示语句
                    reject(res.data.msg);
                }
            }
        })
        .catch(err => {
            reject(err);
        })
    })
}