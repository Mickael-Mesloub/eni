export interface User {
    username: string;
    email: string;
    password: string;
}

export interface UserInStorage {
    username: string;
    token: string;
}