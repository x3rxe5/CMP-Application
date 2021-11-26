import React,{ useState } from 'react';
import axios from "axios";
import { ToastContainer } from 'react-toastify';
import ToastComponent from '../components/ToastComponent';
import 'react-toastify/dist/ReactToastify.css';
// import Calendar from "react-calendar";
// import 'react-calendar/dist/Calendar.css';
import { DatePickerComponent } from "@syncfusion/ej2-react-calendars";
import Constants from '../Constant';


export default function Signup() {

  const [user,setUser] = useState({
    username:'',
    email:'',
    password:'',
    firstName:"",
    lastName:"",
    dob:""
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('username', user.username);
    formData.append('email', user.email);
    formData.append('password', user.password);
    formData.append('dob', user.dob);
    formData.append('firstName', user.firstName);
    formData.append('lastName', user.lastName);


    if(user.username !== '' && user.email !== '' && user.password !== '' && user.firstName !== '' && user.lastName !== '' && user.dob !== ''){
      console.log(user);
      axios.post(Constants.Backend_URL+'api/v1/users/register', formData, { withCredentials:true })
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

  const handleCalendarChange = (e) => {
    console.log("this is e target name ",e.target.name);
    const _d = new Date(e.target.value);        
    const convertedDateString = _d.getFullYear()+"/"+(parseInt(_d.getMonth())+1)+"/"+_d.getDate();    
    setUser({...user,[e.target.name]:convertedDateString})    
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
                <div className="flex flex-1 w-full">
                  <div className='w-1/2'>
                    <div className="text-sm font-bold text-gray-700 tracking-wide">
                      First Name
                    </div>
                    <input
                      className="w-1/2 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                      type="text"
                      placeholder="Joe"        
                      name="firstName"                 
                      onChange={handleChange}
                    />
                  </div>
                  <div className='w-1/2'>
                    <div className="text-sm font-bold text-gray-700 tracking-wide">
                      Last Name
                    </div>
                    <input
                      className="w-1/2 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                      type="text"
                      placeholder="Doe"        
                      name="lastName"    
                      onChange={handleChange}
                    />
                  </div>
                </div>
                  <div className="flex flex-1 w-full">
                    <div className="mt-5 w-1/2">
                      <div className="text-sm font-bold text-gray-700 tracking-wide">
                        Email Address
                      </div>
                      <input
                        className="w-1/2 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                        type="email"
                        name="email"
                        placeholder="mike@gmail.com"
                        onChange={handleChange}
                      />
                    </div>
                    <div className="mt-5 w-1/2">
                      <div className="flex justify-between items-center">
                        <div className="text-sm font-bold text-gray-700 tracking-wide">
                          Password
                        </div>                    
                      </div>
                      <input
                        className="w-1/2 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                        type="password"
                        name="password"
                        placeholder="Enter your password"
                        onChange={handleChange}
                      />
                    </div>                            
                  </div>


                <div className="flex flex-1 w-full mt-6">
                  <div className='w-1/2'>
                    <div className="text-sm font-bold text-gray-700 tracking-wide">
                      Username
                    </div>
                    <input
                      className="w-1/2 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                      type="text"
                      placeholder=""        
                      name="username"                 
                      onChange={handleChange}
                    />
                  </div>
                  <div className='w-1/2'>
                    <div className="text-sm font-bold text-gray-700 tracking-wide">
                      Date of birth
                    </div>
                    <DatePickerComponent
                      name="dob"
                      format='dd-MMM-yy'                     
                      onChange={handleCalendarChange}
                    />
                    {/* <Calendar 
                      onChange={handleChange}
                      selectRange={true}
                      defaultView='decade'
                    /> */}
                    {/* <input
                      className="w-1/2 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-indigo-500"
                      type="text"
                      placeholder="birth date"        
                      name="dob"    
                      onChange={handleChange}
                    /> */}
                  </div>
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