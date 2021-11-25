import React,{ useState } from 'react';
import { Link } from "react-router-dom";
import { ToastContainer } from 'react-toastify';
import ToastComponent from '../components/ToastComponent';
import axios from "axios";


const Login = () => {
    const [user,setUser] = useState(
      {
        email:"",
        password:""
      }
    );

    const handleSubmit = async (e) => {
      e.preventDefault();
      const formData = new FormData();
      formData.append("email",user.email);
      formData.append("password",user.password);
      console.log(user);
      await axios.post("http://localhost:5000/login",user,{ withCredentials:true }).then(res => {
        console.log(res);
        if(res.status === 201){
          new ToastComponent("You are successfully logged in").onSuccessMessage();
        }else if(res.status === 400){          
          new ToastComponent(res.data).onProperFailureMessage();
        }else{          
          new ToastComponent("Unknown Error Occured").onUnknownFailure();
        }
      }).catch(err => {
        new ToastComponent("Check Your Internet Connection").onUnknownFailure();
      })
    }

    const handleChange = (e) => {
      setUser({...user, [e.target.name]: e.target.value});
    }

    // const testCookie = () => {
    //   axios.get("http://localhost:5000/cookie",{ withCredentials:true }).then(res => {
    //     console.log(res);
    //   }).catch(err => {
    //     console.log(err);
    //   })
    // }

    return (
      <>
        {/* <button onClick={testCookie}>TestCookie</button> */}
        <ToastContainer />
        <div className="h-3/4">
          <div className="max-w-screen-l m-0 sm:m-20 bg-white shadow sm:rounded-lg flex justify-center flex-1">
            <div className="lg:w-1/2 xl:w-5/12 p-6 sm:p-12">
              <div>              
              </div>
              <div className="mt-12 flex flex-col items-center">
                <h1 className="text-2xl xl:text-3xl font-extrabold">
                  Login 
                </h1>
                <div className="w-full flex-1 mt-8">


                  <div className="mx-auto max-w-xs">
                    <form onSubmit={handleSubmit}>                    
                    <input
                      className="w-full px-8 py-4 rounded-lg font-medium bg-gray-100 border border-gray-200 placeholder-gray-500 text-sm focus:outline-none focus:border-gray-400 focus:bg-white"
                      type="email"
                      name="email"
                      onChange={handleChange}
                      placeholder="Email"
                    />
                    <input
                      className="w-full px-8 py-4 rounded-lg font-medium bg-gray-100 border border-gray-200 placeholder-gray-500 text-sm focus:outline-none focus:border-gray-400 focus:bg-white mt-5"
                      type="password"
                      onChange={handleChange}
                      name="password"
                      placeholder="Password"
                    />
                    <button className="mt-5 tracking-wide font-semibold bg-indigo-500 text-gray-100 w-full py-4 rounded-lg hover:bg-indigo-700 transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none" type="submit">
                      <svg
                        className="w-6 h-6 -ml-2"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth={2}
                        strokeLinecap="round"
                        strokeLinejoin="round"
                      >
                        <path d="M16 21v-2a4 4 0 00-4-4H5a4 4 0 00-4 4v2" />
                        <circle cx="8.5" cy={7} r={4} />
                        <path d="M20 8v6M23 11h-6" />
                      </svg>
                      <span className="ml-3">Log in</span>
                    </button>
                    </form>
                    <p className="mt-6 text-xs text-gray-600 text-center">
                      I agree to abide by templatana's
                      <Link
                        to="#"
                        className="border-b border-gray-500 border-dotted m-1"
                      >
                        Terms of Service
                      </Link>
                      and its
                      <Link
                        to="#"
                        className="border-b border-gray-500 border-dotted m-1"
                      >
                        Privacy Policy
                      </Link>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className="flex-1 bg-indigo-100 text-center hidden lg:flex">
              <div
                className="m-12 xl:m-16 w-full bg-contain bg-center bg-no-repeat"
                style={{
                  backgroundImage:
                    'url("https://storage.googleapis.com/devitary-image-host.appspot.com/15848031292911696601-undraw_designer_life_w96d.svg")',
                }}
              />
            </div>
          </div>
          <div
            className="REMOVE-THIS-ELEMENT-IF-YOU-ARE-USING-THIS-PAGE hidden treact-popup fixed inset-0 flex items-center justify-center"
            style={{ backgroundColor: "rgba(0,0,0,0.3)" }}
          >
            <div className="max-w-lg p-8 sm:pb-4 bg-white rounded shadow-lg text-center sm:text-left">
              <h3 className="text-xl sm:text-2xl font-semibold mb-6 flex flex-col sm:flex-row items-center">
                <div className="bg-green-200 p-2 rounded-full flex items-center mb-4 sm:mb-0 sm:mr-2">
                  <svg
                    className="text-green-800 inline-block w-5 h-5"
                    fill="none"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth={2}
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path d="M11 5.882V19.24a1.76 1.76 0 01-3.417.592l-2.147-6.15M18 13a3 3 0 100-6M5.436 13.683A4.001 4.001 0 017 6h1.832c4.1 0 7.625-1.234 9.168-3v14c-1.543-1.766-5.067-3-9.168-3H7a3.988 3.988 0 01-1.564-.317z" />
                  </svg>
                </div>
                Free TailwindCSS Component Kit!
              </h3>
              <p>
                I recently released Treact, a{" "}
                <span className="font-bold">free</span> TailwindCSS Component
                Kit built with React.
              </p>
              <p className="mt-2">
                It has 52 different UI components, 7 landing pages, and 8 inner
                pages prebuilt. And they are customizable!
              </p>
              <div className="mt-8 pt-8 sm:pt-4 border-t -mx-8 px-8 flex flex-col sm:flex-row justify-end leading-relaxed">
                <button className="close-treact-popup px-8 py-3 sm:py-2 rounded border border-gray-400 hover:bg-gray-200 transition duration-300">
                  Close
                </button>
                <Link
                  className="font-bold mt-4 sm:mt-0 sm:ml-4 px-8 py-3 sm:py-2 rounded bg-purple-700 text-gray-100 hover:bg-purple-900 transition duration-300 text-center"
                  href="https://treact.owaiskhan.me"
                  target="_blank"
                >
                  See Treact
                </Link>
              </div>
            </div>
          </div>
        </div>
      </>
    );
}

export default Login;