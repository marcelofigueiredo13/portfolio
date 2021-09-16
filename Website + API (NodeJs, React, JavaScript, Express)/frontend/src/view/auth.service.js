import axios from "axios";


class AuthService {
  
async login(u_email, u_password) {
    return await axios
        .post("xxx/utilizador/login", {u_email, u_password})
        .then(res => {
                if (res.data.token) {
                localStorage.setItem("user", JSON.stringify(res.data));
                }
        return res.data;
        }, reason => { 
              return 0;
        });
}

logout() { localStorage.removeItem("user"); }

getCurrentUser() { 
  return JSON.parse(localStorage.getItem('user'));
}

} 

export default new AuthService();