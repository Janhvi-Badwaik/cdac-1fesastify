import React, { useState } from "react";
import FoodDisplay from "../../Components/FoodDisplay/FoodDisplay";

const ExploreFood = () => {
  const [category, setCategory] = useState("All");
  const [searchText, setSearchText] = useState("");
  return (
    <>
      <div className="container mt-1">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <form onSubmit={(e) => e.preventDefault()}>
              <div className="d-flex align-items-center gap-2">
                <select
                  className="form-select"
                  style={{ maxWidth: "150px" }}
                  onChange={(e) => setCategory(e.target.value)}
                >
                  <option value="All">All</option>
                  <option value="Biryani">Biryani</option>
                  <option value="Burger">Burger</option>
                  <option value="Cake">Cake</option>
                  <option value="Ice cream">Ice cream</option>
                  <option value="Pizza">Pizza</option>
                  <option value="Rolls">Rolls</option>
                  <option value="Salad">Salad</option>
                </select>

                <input
                  type="text"
                  className="form-control mt-2"
                  placeholder="Search your favorite dish..."
                  onChange={(e) => setSearchText(e.target.value)}
                  value={searchText}
                />

                <button type="submit" className="btn btn-primary">
                  <i className="bi bi-search"></i>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>

      <div className="mt-1">
        <FoodDisplay category={category} searchText={searchText} />
      </div>
    </>
  );
};

export default ExploreFood;
