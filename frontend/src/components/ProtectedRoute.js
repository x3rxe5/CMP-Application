
import React from "react";
import { useSelector } from "react-redux";
import { Redirect, Route } from "react-router-dom";

function ProtectedRoute({ component: Component, ...restOfProps }) {
  const { loggedInValue } =  useSelector(state => state.authenticationReducers);
  

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