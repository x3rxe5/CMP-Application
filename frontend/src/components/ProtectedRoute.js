import axios from "axios";
import React,{ useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Redirect, Route } from "react-router-dom";
import { isAuth } from "../slices/AuthSlices";

function ProtectedRoute({ component: Component, ...restOfProps }) {
  const { loggedInValue } =  useSelector(state => state.authenticationReducers);
    
  const dispatch = useDispatch();
    
  useEffect(() => {
    axios.get("http://localhost:8080/api/v1/users/read-cookie",{ withCredentials:true })
    .then(res => {        
        if(res.data.user === '1'){          
          dispatch(isAuth());
        }
    })
    .catch(err => console.log(err));
  },[dispatch]);

  return (
    <Route
      {...restOfProps}
      render={(props) =>
        loggedInValue ? <Component {...props} /> : <Redirect to="/login" />
      }
    />
  );
}

export default ProtectedRoute;