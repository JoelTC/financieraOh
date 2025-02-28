export interface IResponseData<K>{
    codigo?: string
    mensaje?: string
    dataResponse?:K[]
    entity?: K
}