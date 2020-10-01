import {GET_PERSON, GET_PERSONS} from "../actions/types"

const initialState = {
    persons: [],
    person: {}
};

// const booleanActionPayload = payload => {
//     if(payload){
//         return true;
//     }else{
//         return false;
//     }
// };

export default function (state = initialState, action) {
    switch (action.type){
        case GET_PERSONS:
        return{
            ...state,
            persons: action.payload,
        };
        case GET_PERSON:
        return{
            ...state,
            persons: action.payload,
        };
        default:
            return state;
    }
}
