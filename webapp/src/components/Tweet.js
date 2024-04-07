import { useState, useEffect, useContext } from "react";
import {TweetsContext} from "../contexts/TweetsContext";

const Tweet = () => {

    const tweetsContext = useContext(TweetsContext)
    const [tweet, setTweet] = useState("");
    const postTweet = (event) => {
        event.preventDefault();

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({content: tweet})
        };

        fetch('/tweets/api/v1/tweets', options)
            .then(res => res.json())
            .then(data => {
                tweetsContext.dispatch({type: 'ADD', payload: data})
                setTweet("")
            })
            // can add error toast
            .catch(err => console.log(err));
    }

    return (
        <form className="col-12 mt-4" onSubmit={postTweet}>
            <div className="mb-1">
                <textarea className="form-control" placeholder="tweet to the world!" 
                    id="tweetContent" rows="3" value={tweet} onChange={(e) => setTweet(e.target.value)} />
            </div>
            <div className="text-end">
                <button type="submit" className="btn btn-primary mb-3">Chirp!</button>
            </div>
        </form>
        

    );
}
 
export default Tweet;