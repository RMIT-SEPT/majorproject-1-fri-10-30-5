export const LOGIN = "LOGIN";
export const LOGOUT = "LOGOUT";

export const login = user => {
  return {
    type: LOGIN,
    payload: user
  };
};

export const logout = () => {
  return {
    type: LOGOUT
  };
};

export const authenticate = () => {

  const createHistory = require("history").createBrowserHistory;

  let history = createHistory();

  const token = localStorage.getItem("jwtToken")
  const username = localStorage.getItem("AGMEuser")
  const userType = localStorage.getItem("userType")

  if(username === null || username === undefined) {

    console.log("no user")
    history.push("/")
    let pathUrl = window.location.href;
    window.location.href = pathUrl;  
  }

  const user = {
    username: username,
    userType: userType
  }

  return user;
}

export const checkUser = () => {

  const token = localStorage.getItem("jwtToken")
  const username = localStorage.getItem("AGMEuser")
  const userType = localStorage.getItem("userType")

  let res = false;

  console.log("un: ", username)
  if(username !== null && username !== undefined) {

    console.log("user found")
    res = true
  }

  return res;
}