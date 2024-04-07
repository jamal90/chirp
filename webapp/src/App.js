import { createContext } from 'react';
import './App.css';
import Feeds from './components/Feeds';
import PageHeader from './components/PageHeader';
import Tweet from './components/Tweet';
import {TweetsProvider} from './contexts/TweetsContext';
import NavBar from './components/NavBar';
import { Route, Routes } from 'react-router';
import UserList from './components/UserList';

function App() {  



  return (
    <div className="App">
      <TweetsProvider>
        <PageHeader />
        <div className="container">
          <Tweet />
          <NavBar />
          <Routes>
            <Route path="/web/" element={<Feeds />} />
            <Route path="/web/feeds" element={<Feeds />} />
            <Route path="/web/users" element={<UserList />} />
          </Routes>
        </div>
      </TweetsProvider>
    </div>
  );
}

export default App;
