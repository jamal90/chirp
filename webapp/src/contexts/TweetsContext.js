import { createContext, useReducer } from 'react';
import tweetsReducer from './../reducers/TweetsReducer';

export const TweetsContext = createContext()
export const TweetsProvider = ({children}) => {
    const [state, dispatch] = useReducer(tweetsReducer, []);
    return (
        <TweetsContext.Provider value={{state, dispatch}}>
            {children}
        </TweetsContext.Provider>
    );
}