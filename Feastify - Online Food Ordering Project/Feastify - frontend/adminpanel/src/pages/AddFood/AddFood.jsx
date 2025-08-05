import React, {useState } from 'react';
import {assets} from '../../assets/assets';
import axios from 'axios';
import { addFood } from '../../services/foodService';
import { toast } from 'react-toastify';

const AddFood = () => {
    const[image, setImage] = useState(false);
    const [data, setData] = useState({
        name: '',
        description: '',
        price: '',
        category: '',
        isVeg: ''
    });

    const onChangeHandler = (event) => {
      const name = event.target.name;
      const value = event.target.value;
      setData(Data => ({...data, [name]: value}));
    }

    const onSubmitHandler = async (event) => {
      event.preventDefault();
      if(!image){
        toast.error('please select an image.');
        return;
      }

      try{
        await addFood(data, image);
        toast.success('Food added successfully');
        setData({name:'',description:'', category:'',price:'',isVeg:''});
        setImage(null);
      }catch(error){
        toast.error('Error adding food');
      }

      
    }

  return (
    <div className="mx-2 mt-2">
      <div className="row">
        <div className="card col-md-4">
          <div className="card-body">
            <h2 className="mb-4">Add Food</h2>
            <form onSubmit={onSubmitHandler}>

            <div className="mb-3">
                <label htmlFor="image" className="form-label">
                      <img src = {image ? URL.createObjectURL(image) : assets.upload} alt = "" width={98}/>
                  </label>
                <input type="file" className="form-control" id="image" hidden onChange={(e) => setImage(e.target.files[0])}/>
              </div>

              <div className="mb-3">
                <label htmlFor="name" className="form-label">Name</label>
                <input type="text" className="form-control" id="name" placeholder='add food name'required name='name' onChange={onChangeHandler} value={data.name}/>
              </div>

              <div className="mb-3">
                <label htmlFor="description" className="form-label">Description</label>
                <textarea className="form-control" id="description" rows="5" placeholder='add description here'required name='description' onChange={onChangeHandler} value={data.description}></textarea>
              </div>

              <div className="mb-3">
                <label htmlFor="price" className="form-label">Price</label>
                <input type="number" className="form-control" id="price" placeholder='&#8377;200'required name='price' onChange={onChangeHandler} value={data.price}/>
              </div>

              <div className="mb-3">
                <label htmlFor="category" className="form-label">Category</label>
                <select name="category" id="category" className='form-control' onChange={onChangeHandler} value={data.category}>
                  <option value="">Select Category</option>
                  <option value="Biryani">Biryani</option>
                  <option value="Cake">Cake</option>
                  <option value="Burger">Burger</option>
                  <option value="Pizza">Pizza</option>
                  <option value="Rolls">Rolls</option>
                  <option value="Salad">Salad</option>
                  <option value="Ice Cream">Ice Cream</option>
                </select>
              </div>

              <div className="mb-3">
                <label className="form-label d-block">Is Veg?</label>
                <div className="form-check form-check-inline">
                  <input className="form-check-input" type="radio" name="isVeg" id="veg" value="true" checked={data.veg === "true"}              
                    onChange={onChangeHandler}  />
                  <label className="form-check-label" htmlFor="veg">Veg</label>
                </div>
                <div className="form-check form-check-inline">
                  <input className="form-check-input" type="radio" name="isVeg" id="nonVeg" value="false" checked={data.veg === "false"}             
                    onChange={onChangeHandler}/>
                  <label className="form-check-label" htmlFor="nonVeg">Non-Veg</label>
                </div>
              </div>

              <button type="submit" className="btn btn-primary">Add Food</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AddFood;
