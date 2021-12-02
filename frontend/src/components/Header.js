import { Link } from "react-router-dom";
import { useSelector,useDispatch } from "react-redux";
import { useEffect } from "react";
import { loggedOutFromReducers } from "./../slices/AuthSlices";

const Header = () => {
    const { loggedInValue } = useSelector(state => state.authenticationReducers);
    
    useEffect(() => {

    },[]);

  

    return (
        <header className="text-gray-600 body-font">
            <div className="container mx-auto flex flex-wrap p-5 flex-col md:flex-row items-center">
                <nav className="flex lg:w-2/5 flex-wrap items-center text-base md:ml-auto">
                    <Link className="mr-5 hover:text-gray-900" to="/">Home</Link>
                    <Link className="mr-5 hover:text-gray-900">About</Link>
                    <Link className="mr-5 hover:text-gray-900">Services</Link>
                    
                </nav>
                <Link className="flex order-first lg:order-none lg:w-1/5 title-font font-medium items-center text-gray-900 lg:items-center lg:justify-center mb-4 md:mb-0" to="/">
                    {/* <svg
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        stroke="currentColor"
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth={2}
                        className="w-10 h-10 text-white p-2 bg-indigo-500 rounded-full"
                        viewBox="0 0 24 24"
                    >
                        <path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5" />
                    </svg> */}
                    <img src="https://img.icons8.com/color/96/000000/chat--v3.png" alt="logo_png" className="m-2"/>
                    {/* <img src="https://img.icons8.com/ios/50/000000/computer-chat--v1.png" className="m-2 text-green-200" alt="logo_image" /> */}
                    <span className="ml-3 text-xl">Custom Message</span>
                </Link>
                <div className="lg:w-2/5 inline-flex lg:justify-end ml-5 lg:ml-0">
                    {
                        loggedInValue && loggedInValue 
                        ? <>
                            <DashboardComponentLink />
                        </> 
                        : <>
                            <LoginAndRegisterComponent />
                        </>
                    }
                    
                </div>

                
            </div>
        </header>
    );
}

const LoginAndRegisterComponent = () => {
    return(
        <>
            <Link className="inline-flex items-center bg-gray-100 border-0 py-1 px-3 focus:outline-none hover:bg-gray-200 rounded text-base mt-4 md:mt-0" to="/register">
                Register                        
            </Link>

            <Link className="inline-flex items-center bg-green-300 border-0 py-1 px-3 focus:outline-none hover:bg-green-400 rounded text-base mt-4 md:mt-0 md:ml-2" to="/login">
                Login
                <svg
                    fill="none"
                    stroke="currentColor"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth={2}
                    className="w-4 h-4 ml-1"
                    viewBox="0 0 24 24"
                >
                    <path d="M5 12h14M12 5l7 7-7 7" />
                </svg>
            </Link>
        </>
    )
}

const DashboardComponentLink = () => {
    const dispatch = useDispatch();
    const logoutClick = () => {
        dispatch(loggedOutFromReducers);
    }
    return(
        <>
            <Link className="inline-flex items-center bg-gray-100 border-0 py-1 px-3 focus:outline-none hover:bg-gray-200 rounded text-base mt-4 md:mt-0" to="/chatroom">
                Chatroom 
                <svg
                    fill="none"
                    stroke="currentColor"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth={2}
                    className="w-4 h-4 ml-1"
                    viewBox="0 0 24 24"
                >
                    <path d="M5 12h14M12 5l7 7-7 7" />
                </svg>
            </Link>

            <button className="inline-flex items-center bg-red-100 border-0 py-1 px-3 focus:outline-none hover:bg-red-300 rounded text-base mt-4 md:mt-0 ml-2" onClick={logoutClick}>
                Logout
            </button>
        </>
    )
}

export default Header;