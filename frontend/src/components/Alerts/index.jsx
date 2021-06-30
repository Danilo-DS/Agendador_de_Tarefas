import { toast } from 'react-toastify';

export default function Alerte(msg, tipo){
    return toast(msg, {position: toast.POSITION.TOP_CENTER, type : tipo})
}
