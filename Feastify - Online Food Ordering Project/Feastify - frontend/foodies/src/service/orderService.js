/*import axios from "axios";

const API_URL = "http://localhost:8080/orders";

export const fetchUserOrders = async (token) => {
    try {
        const response = await axios.get(API_URL, {
            headers: { Authorization: `Bearer ${token}` },
        }); 
        return response.data;
    } catch (error) {
        console.error('Error occured while fetching the orders', error);
        throw error;
    }
}

export const createOrder = async (orderData, token) => {
    try {
        const response = await axios.post(
            API_URL+"/create",
            orderData,
            { headers: { Authorization: `Bearer ${token}` } }
        );
        return response.data;
    } catch (error) {
        console.error('Error occured while creating the order', error);
        throw error;
    }
}

export const verifyPayment = async (paymentData, token) => {
    try {
        const response = await axios.post(
            API_URL+"/verify",
            paymentData,
            { headers: { Authorization: `Bearer ${token}` } }
        );
        return response.status === 200;
    } catch (error) {
        console.error('Error occured while verifing the payment', error);
        throw error;
    }
}

export const deleteOrder = async (orderId, token) => {
    try {
        await axios.delete(API_URL+"/"+ orderId, {
            headers: { Authorization: `Bearer ${token}` },
        });
    } catch (error) {
        console.error('Error occured while deleting the order', error);
        throw error;
    }
}*/
import axios from "axios";

const API_URL = "http://localhost:8080/orders";

const getAuthHeader = (token) =>
  token ? { Authorization: `Bearer ${token}` } : {};

export const fetchUserOrders = async (token) => {
  try {
    if (!token) throw new Error("Token missing while fetching orders");
    const response = await axios.get(API_URL, {
      headers: getAuthHeader(token),
    });
    return response.data;
  } catch (error) {
    console.error("Error occurred while fetching the orders", error);
    throw error;
  }
};

export const createOrder = async (orderData, token) => {
  try {
    if (!token) throw new Error("Token missing while creating order");
    const response = await axios.post(
      API_URL + "/create",
      orderData,
      { headers: getAuthHeader(token) }
    );
    return response.data;
  } catch (error) {
    console.error("Error occurred while creating the order", error);
    throw error;
  }
};

export const verifyPayment = async (paymentData, token) => {
  try {
    if (!token) throw new Error("Token missing while verifying payment");
    const response = await axios.post(
      API_URL + "/verify",
      paymentData,
      { headers: getAuthHeader(token) }
    );
    return response.status === 200;
  } catch (error) {
    console.error("Error occurred while verifying the payment", error);
    throw error;
  }
};

export const deleteOrder = async (orderId, token) => {
  try {
    if (!token) throw new Error("Token missing while deleting order");
    await axios.delete(`${API_URL}/${orderId}`, {
      headers: getAuthHeader(token),
    });
  } catch (error) {
    console.error("Error occurred while deleting the order", error);
    throw error;
  }
};
