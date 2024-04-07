export default (currTweets, action) => {
    const {type, payload} = action;
    switch(type) {
        case 'LOAD': 
            return payload;
        case 'ADD': 
            return [payload, ...currTweets];
        default:
            return currTweets;
    }
}
