import axios from "axios";
import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import "./ListFood.css";
import { deleteFood, getFoodList } from "../../services/foodService";

const ListFood = () => {
  const [list, setList] = useState([]);

  const fetchList = async () => {
    try {
      const data = await getFoodList();
      console.log("Fetched food list (full):", JSON.stringify(data, null, 2)); // ✅ clear structure

      // ✅ Handle common formats
      if (Array.isArray(data)) {
        setList(data);
      } else if (Array.isArray(data.content)) {
        setList(data.content);
      } else if (Array.isArray(data.foods)) {
        setList(data.foods);
      } else {
        // Commented out to let console show the object clearly
        // toast.error("Invalid food list format received.");
        // console.error("Unexpected food list format:", data);
      }
    } catch (error) {
      toast.error("Error while reading the foods.");
    }
  };

  const removeFood = async (foodid) => {
    try {
      const success = await deleteFood(foodid);
      if (success) {
        toast.success("Food removed.");
        await fetchList();
      } else {
        toast.error("Error occurred while deleting the food.");
      }
    } catch (error) {
      toast.error("Error occurred while deleting the food.");
    }
  };

  useEffect(() => {
    fetchList();
  }, []);

  return (
    <div className="py-5 row justify-content-center">
      <div className="col-11 card">
        <table className="table">
          <thead>
            <tr>
              <th>Image</th>
              <th>Name</th>
              <th>Description</th>
              <th>Category</th>
              <th>Price</th>
              <th>IsVeg</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {Array.isArray(list) && list.length > 0 ? (
              list.map((item, index) => (
                <tr key={index}>
                  <td>
                    <img src={item.imageURL} alt="" height={48} width={48} />
                  </td>
                  <td>{item.name}</td>
                  <td>{item.description}</td>
                  <td>{item.category}</td>
                  <td>&#8377;{item.price}.00</td>
                  <td>{item.veg ? "Veg" : "Non-Veg"}</td>
                  <td className="text-danger">
                    <i
                      className="bi bi-trash-fill fs-4"
                      onClick={() => removeFood(item.id)}
                    ></i>
                  </td>
                </tr>
              ))
            ) : (           
              <tr>
                <td colSpan="7" className="text-center">
                  No food items found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListFood;
