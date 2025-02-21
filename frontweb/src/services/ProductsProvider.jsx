// import { createContext, useEffect, useState } from "react";

// export const ProductsContext = createContext();

// export const ProductsProvider = ({ children }) => {
//     const [products, setProducts] = useState([]);

//     useEffect(() => {
//         fetch("http://localhost:8080/product/all")
//         .then(response => response.json())
//         .then(data => setProducts(data))
//         .catch(error => console.log("Error while getting products: ", error))
//     }, [])

//     return (
//         <ProductsContext.Provider value={{ products }}>
//             {children}
//         </ProductsContext.Provider>
//     )
// }