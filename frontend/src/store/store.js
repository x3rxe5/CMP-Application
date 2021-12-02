import { configureStore } from '@reduxjs/toolkit';
import authReducers from "./../slices/AuthSlices";
export default configureStore({
    reducer: {
        authenticationReducers:authReducers
    },
});