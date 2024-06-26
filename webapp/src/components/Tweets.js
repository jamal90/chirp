import { useContext, useEffect, useState } from "react";
import placeholder from './../logo.svg';
import {TweetsContext} from "../contexts/TweetsContext";

const Tweets = () => {

    const {state: tweets, dispatch} = useContext(TweetsContext);

    useEffect(() => {
        fetch('http://localhost:8080/tweets/api/v1/tweets')
            .then(res => res.json())
            .then(data => dispatch({type: 'LOAD', payload: data}))
            .catch(err => console.log(err));

        // todo - clean up function
    }, []);

    return (
        <div className="ms-2 me-2">
            {
                tweets.map(tweet => (
                    <div className="row mb-3 pt-1 pb-1 feed-item" key={tweet.id}>
                        <div className="col-1 align-self-center">
                            <img src={placeholder} alt="user profile picture" className="img-fluid rounded-circle" />
                        </div>
                        <div className="col-11 text-start align-self-lg-center text-light">
                            <h5>{tweet.userFirstName}</h5>
                            <p> {tweet.content} </p>
                            <small className="text-muted">{new Date(tweet.createdAt).toLocaleString()}</small>
                        </div>
                    </div>
                ))
            }
        </div>
    );
}

export default Tweets;