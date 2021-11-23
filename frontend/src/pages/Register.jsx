import React,{ useState } from 'react';
import axios from "axios";
import { ToastContainer } from 'react-toastify';
import ToastComponent from '../components/ToastComponent';
import 'react-toastify/dist/ReactToastify.css';

export default function Signup() {
  const [user,setUser] = useState({
    username:'',
    email:'',
    password:'',
    confirmPassword:'',
    photo:''
  });

  
  

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('username', user.username);
    formData.append('email', user.email);
    formData.append('password', user.password);
    formData.append('confirmPassword', user.confirmPassword);
    formData.append('photo', user.photo);

    if(user.username !== '' && user.email !== '' && user.password !== '' && user.confirmPassword !== '' && user.photo !== ''){
      console.log(user);
      axios.post('http://localhost:5000/signup', formData, { withCredentials:true })
      .then(res => {
        console.log(`this is status -> `,res.status);
        if(res.status === 201){
          new ToastComponent("You are successfully logged in").onSuccessMessage();          
        }else if(res.status === 400){
          new ToastComponent(res.message).onProperFailureMessage();
        }else{
          new ToastComponent("You are successfully logged in").onUnknownFailure();
        }
      }).catch(err => {
        console.log(err);
      });
    }
    
    // Main event to First
  }

  const handleChange = (e) => {
    setUser({...user, [e.target.name]: e.target.value});
  }


  
  return (
    <>
    <ToastContainer />
    <div className=" bg-grey-400 mb-4 p-12">
      <div className="lg:flex items-center justify-center">
        <div className="lg:w-full xl:max-w-screen-sm">
          
          <div className="mt-5 px-12 sm:px-24 md:px-48 lg:px-12 lg:mt-16 xl:px-24 xl:max-w-2xl">
            <h2
              className="text-center text-4xl text-indigo-900 font-display font-semibold lg:text-left xl:text-5xl
            xl:text-bold"
            >
              Sign Up
            </h2>
            <div className="mt-12">
              <form onSubmit={handleSubmit} encType='multipart/form-data'> 
                <div>
                  <div className="text-sm font-bold text-gray-700 tracking-wide">
                    Username
                  </div>
                  <input
                    className="w-full text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                    type="text"
                    placeholder=""        
                    name="username"                 
                    onChange={handleChange}
                  />
                </div>
                <div className="mt-5">
                  <div className="text-sm font-bold text-gray-700 tracking-wide">
                    Email Address
                  </div>
                  <input
                    className="w-full text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                    type="email"
                    name="email"
                    placeholder="mike@gmail.com"
                    onChange={handleChange}
                  />
                </div>
                <div className="mt-5">
                  <div className="flex justify-between items-center">
                    <div className="text-sm font-bold text-gray-700 tracking-wide">
                      Password
                    </div>                    
                  </div>
                  <input
                    className="w-full text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                    type="password"
                    name="password"
                    placeholder="Enter your password"
                    onChange={handleChange}
                  />
                </div>
                <div className='mt-5'>
                  <div className="text-sm font-bold text-gray-700 tracking-wide">
                    Confirm Password
                  </div>
                  <input
                    className="w-full text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                    type="password"
                    name="confirmPassword"
                    placeholder=""
                    onChange={handleChange}
                  />
                </div>                
                <div className="mt-10">
                  <button
                    className="bg-indigo-500 text-gray-100 p-4 w-full rounded-full tracking-wide
                        font-semibold font-display focus:outline-none focus:shadow-outline hover:bg-indigo-600
                        shadow-lg"
                      type="submit"
                  >
                    Sign Up
                  </button>
                </div>               
              </form>               
            </div>
          </div>
        </div>       
      </div>      
    </div>
    </>
  );
}