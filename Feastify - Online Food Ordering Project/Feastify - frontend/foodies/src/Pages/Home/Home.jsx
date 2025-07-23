import React from 'react';
import Header from '../../Components/Header/Header';
import ExploreMenu from '../../Components/ExploreMenu/ExploreMenu';
import FoodDisplay from '../../Components/FoodDisplay/FoodDisplay'

const Home = () => {
  return (
    <div>
      <main className='container'>
        <Header />
        <ExploreMenu />
        <FoodDisplay />
      </main>
    </div>
  )
}

export default Home;
