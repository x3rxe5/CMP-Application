import axios from "axios";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import {
  BrowserRouter as Router, Route,Switch
} from "react-router-dom";
import Footer from "./components/Footer";
// Components
import Header from "./components/Header";
import ProtectedRoute from "./components/ProtectedRoute";
import ChatRoom from "./pages/ChatRoom";
// Pages
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import { isAuth } from "./slices/AuthSlices";


// Pages

function App() {
  
  const dispatch = useDispatch();
  useEffect(() => {
      axios.get("http://localhost:8080/validate-cookie",{ withCredentials:true })
      .then(res => {
          if(res.data === 1){
            dispatch(isAuth());
          }
      })
      .catch(err => console.log(err));
  },[dispatch]);

  return (
    <>        
      <Router>
        <ProtectedRoute exact path="/chatroom" component={ChatRoom} />                
        <Header />
          <Route exact path="/">
            <Home />
          </Route>          
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/login">
            <Login />
          </Route>  
        <Footer />        
      </Router>
    </>
  );
}

export default App;
