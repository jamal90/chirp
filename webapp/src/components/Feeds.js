import { useEffect, useState } from "react";
import placeholder from './../logo.svg';
import {TweetsContext} from "../contexts/TweetsContext";

const Tweets = () => {

    const [feeds, setFeeds] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/feeds/api/v1/feeds')
            .then(res => res.json())
            .then(data => setFeeds(data.tweets || []))
            .catch(err => console.log(err));

        // todo - clean up function
    }, []);

    return (
        <div className="ms-2 me-2">
            {
                feeds.map(feed => (
                    <div className="row mb-3 pt-1 pb-1 feed-item" key={feed.id}>
                        <div className="col-1 align-self-center">
                            <img src={placeholder} alt="user profile picture" className="img-fluid rounded-circle" />
                        </div>
                        <div className="col-11 text-start align-self-lg-center text-light">
                            <h5>{feed.firstName}</h5>
                            <p> {feed.content} </p>
                            <small className="text-muted">{new Date(feed.createdAt).toLocaleString()}</small>
                        </div>
                    </div>
                ))
            }
        </div>
    );
}

export default Tweets;