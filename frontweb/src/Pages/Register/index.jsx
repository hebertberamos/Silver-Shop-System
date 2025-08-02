import RegisterForm from "../../Components/Container/forms/registerForm/RegisterForm"
import { useNavigate } from 'react-router-dom'

function Register() {

    const navigate = useNavigate()
    
    function createUser(user) {
        fetch("http://localhost:8080/auth/register", {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(user)
        })
        .then((resp) => resp.json())
        .then((data) => {
            console.log(data)
            navigate("/new_product", {state: { message: "Bem vindo!"}})
        })
        .catch(err => console.log(err))

    }

    return (
        <div>
            <h1>Register page</h1>
            <RegisterForm  handleSubmit={createUser}/>
        </div>
    )
}

export default Register
