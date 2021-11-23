import { toast } from "react-toastify";

class ToastComponent{

    constructor(message){
        this.message = message;
    }

    onSuccessMessage(){
        toast.success(this.message, {
            position: "top-right",
            autoClose: 1500,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
        });
    }

    onProperFailureMessage(){
        toast.error(this.message, {
            position: "top-right",
            autoClose: 1500,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
        });
    }

    onUnknownFailure(){
        toast.warn("Unknown Error Occured", {
            position: "top-right",
            autoClose: 1500,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
        });
    }
}

export default ToastComponent;