import { createSlice } from "@reduxjs/toolkit";

const AuthSlices = createSlice({
    name: "auth",
    initialState: {
     loggedInValue: false
    },
    reducers: {
        isAuth:state => {
            state.loggedInValue = true;
        },
        loggedOutFromReducers: state => {
            state.loggedInValue = false;
        }
    }
});


export const { isAuth,loggedOutFromReducers } = AuthSlices.actions;
export default AuthSlices.reducer;
