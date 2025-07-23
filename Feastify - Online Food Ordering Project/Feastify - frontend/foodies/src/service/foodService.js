import axios from "axios";

const API_URL ='http://localhost:8080/food_items';

export const fetchFoodList = async()=>{
    try{
         const response = await axios.get(API_URL);
         return  response.data;
         //setFoodList((await response).data);
         //console.log((await response).data);
    }
    catch(error){
        console.log('Error',error);
        throw  error;
    }
    }