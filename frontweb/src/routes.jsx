import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import User from "./Pages/User";
// import Login from "./Pages/Login";
import AddNewProdut from "./Pages/Products/AddProductPage/index"
import ProductPage from "./Pages/Products/ProductPage/ProductPage";
import Register from "./Pages/Register";

function AppRoutes() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />}>Home</Route>
                <Route path="/user" element={<User />}>User informations</Route>
                <Route path="/login" element={<Register />}>Login</Route>
                <Route path="/new_product" element={<AddNewProdut />}>AddProduct</Route>
                <Route path="/product/:id" element={ProductPage}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes
