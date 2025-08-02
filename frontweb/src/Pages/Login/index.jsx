<<<<<<< Updated upstream
function Login() {
    return (
        <div>
            <h1>Login page</h1>
            <p>Page to user login in the system</p>
=======
import { useState } from 'react'

function Login() {

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const handleSubmit = (e) =>{
        e.preventDefault();
        console.log("Enail: ", email, "Password: ", password)
    }

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Email</label>
                    <input
                        type='text'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder='Digite seu email'
                        required
                    />
                </div>
                <div>
                    <label>Senha</label>
                    <input 
                        type="text"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder='Digite sua senha'
                        required
                    />
                </div>
                <div>
                    <button>Entrar</button>
                </div>
            </form>
>>>>>>> Stashed changes
        </div>
    )
}

export default Login
