import React, { useEffect, useState, useContext } from "react";
import { StoreContext } from "../../Context/StoreContext";
import axios from "axios";
import { assets } from "../../assets/assets";
import "./MyOrder.css";

const MyOrders = () => {
  const { token } = useContext(StoreContext);
  const [data, setData] = useState([]);

  const fetchOrders = async () => {
    try {
      const response = await axios.get("http://localhost:8080/orders", {
        headers: { Authorization: `Bearer ${token}` }, // ✅ Fixed this line
      });
      setData(response.data);
    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  };

  useEffect(() => {
    if (token) {
      fetchOrders();
    }
  }, [token]);

  return (
    <div className="container">
      <div className="py-5 row justify-content-center">
        <div className="col-11 card">
          <table className="table table-responsive">
            <tbody>
              {data.map((order, index) => (
                <tr key={index}>
                  <td>
                    <img
                      src={assets.delivery}
                      alt="Delivery"
                      height={48}
                      width={48}
                    />
                  </td>
                  <td>
                    {order.orderedItems
                      .map((item) => `${item.name} x${item.quantity}`)
                      .join(", ")}
                  </td>
                  <td>&#8377;{order.amount.toFixed(2)}</td> {/* ₹ symbol */}
                  <td>Items: {order.orderedItems.length}</td>
                  <td className="fw-bold text-capitalize">
                    {order.orderStatus}
                  </td>
                  <td>
                    <button
                      className="btn btn-sm btn-warning"
                      onClick={fetchOrders}
                    >
                      <i className="bi bi-arrow-clockwise"></i>
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default MyOrders;
