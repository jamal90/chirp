import { useEffect, useState } from 'react';
import userPlaceholder from './../user-placeholder.png';
const UserList = () => {

    const [users, setUsers] = useState([])
    useEffect(() => {
        fetch('http://localhost:8080/tweets/api/v1/users')
            .then(res => res.json())
            .then(data => setUsers(data))
            .catch(err => console.log(err));
        // todo - clean up function
    }, []);

    const updateFollowing = (userId) => {
        setUsers(users.map(user => {
            if (user.id == userId) return {...user, following: !user.following}
            else return user
        }));
    }

    const followUser = (userId) => {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: userId})
        }

        fetch('/tweets/api/v1/following', options)
            .then(res => res.status == 201 && updateFollowing(userId))
            .catch(err => console.error("error in following user", err))
    }

    const unfollowUser = (userId) => {
        const options = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: userId})
        }

        fetch('/tweets/api/v1/following', options)
            .then(res => res.status == 204 && updateFollowing(userId))
            .catch(err => console.error("error in following user", err))
    }


    return (
        <div className="col-12 bg-dark text-light">
            {
                users.map(user => {
                    return (
                        <div className="row" key={user.id}>
                            <div className="col-1">
                                <img className="img-fluid rounded-rounded-circle" src={userPlaceholder} alt="user image" />
                            </div>
                            <div className="col-8 text-start">
                                <h4>{user.firstName}</h4>
                                <small>Joined on: {new Date().toDateString()}</small>
                            </div>
                            <div className="col-3 text-end align-self-center">
                                { user.following && <button className="btn btn-secondary bg-bg-secondary " onClick={() => unfollowUser(user.id)}>Unfollow</button> }
                                { !user.following && <button className="btn btn-primary" onClick={() => followUser(user.id)}>Follow</button> }
                            </div>
                        </div>
                    );
                })
            }
        </div>
    );
}
 
export default UserList;