import React, { createContext, useContext } from "react";
import { StoreContext } from "../../Context/StoreContext";
import FoodItem from "../FoodItem/FoodItem";

const FoodDisplay = () =>{
    const {foodList}= useContext(StoreContext)
    return(
        <div className="container">
                <div className="row">
                    {foodList.length>0?(
                        foodList.map((food,index)=>(
                            
                            <FoodItem key={index} 
                            name={food.name} 
                            description={food.description} 
                            id={food.id}
                            imageUrl={food.imageUrl}
                            price={food.price}
                            />
                        
                        ))


                    ):(
                        <div className="text-center mt-4">
                    <h4>food items not found</h4>
                    </div>)}
                </div>
        </div>
    )
}

export default FoodDisplay;
/*import React, { useContext } from "react";
import { StoreContext } from "../../Context/StoreContext";
import FoodItem from "../FoodItem/FoodItem";

const FoodDisplay = () => {
    const { foodList } = useContext(StoreContext);
    console.log("Food list:", foodList);

    return (
        <div className="container">
            <div className="row">
                {foodList && foodList.length > 0 ? (
                    foodList.map((food, index) => (
                        <FoodItem
                            key={index}
                            name={food.name}
                            description={food.description}
                            id={food.id}
                            imageUrl={food.imageUrl}
                            price={food.price}
                        />
                    ))
                ) : (
                    <div className="text-center mt-4">
                        <h4>Food items not found</h4>
                    </div>
                )}
            </div>
        </div>
    );
};

export default FoodDisplay;*/
