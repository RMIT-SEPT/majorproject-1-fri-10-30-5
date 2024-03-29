import axios from 'axios'
import {GET_ERRORS, SET_CURRENT_USER} from "./types"
import jwt_decode from "jwt-decode"
import setJWTToken from "../securityUtils/setJWTToken"

export const createNewUser = (newUser, history) => async dispatch => {
    try{
        await axios.post("/api/user/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    }
    catch(err){
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
}

export const login = LoginRequest => async dispatch => {
    try{
        const res = await axios.post("/api/user/login", LoginRequest);
        const { token } = res.data;
        localStorage.setItem ("jwtToken", token);
        setJWTToken(token);
        const decoded = jwt_decode(token);
        // history.push("/login");
        dispatch({
            type: SET_CURRENT_USER,
            payload: decoded
        });
    }
    catch(err){
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
}
