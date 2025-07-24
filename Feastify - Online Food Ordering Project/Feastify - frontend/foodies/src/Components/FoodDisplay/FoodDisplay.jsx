import React, { useContext } from "react";
import { StoreContext } from "../../Context/StoreContext";
import FoodItem from "../FoodItem/FoodItem";

const FoodDisplay = ({ category, searchText }) => {
  const { foodList } = useContext(StoreContext);

  console.log("Food list:", foodList);
  console.log("Current category:", category);

  const filteredFoods = foodList.filter(
    (food) =>
      (category === "All" || food.category === category) &&
      food.name.toLowerCase().includes(searchText.toLowerCase())
  );

  return (
    <div className="container">
      <div className="row">
        {filteredFoods.length > 0 ? (
          filteredFoods.map((food, index) => (
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
            <h4>food items not found</h4>
          </div>
        )}
      </div>
    </div>
  );
};

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
