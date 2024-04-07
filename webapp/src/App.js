import './App.css';
import Tweets from './components/Tweets';
import Feeds from './components/Feeds';
import PageHeader from './components/PageHeader';
import Tweet from './components/Tweet';
import {TweetsProvider} from './contexts/TweetsContext';
import NavBar from './components/NavBar';
import { Route, Routes } from 'react-router';
import UserList from './components/UserList';

function App() {  
  return (
    <div className="App bg-dark">
      <div className="container">
        <TweetsProvider>
          <PageHeader />
            <Tweet />
            <NavBar />
            <Routes>
              <Route path="/web/" element={<Tweets />} />
              <Route path="/web/feeds" element={<Feeds />} />
              <Route path="/web/users" element={<UserList />} />
            </Routes>
        </TweetsProvider>
      </div>
    </div>
  );
}

export default App;
