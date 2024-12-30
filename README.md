# Android Shopping App

This Android application allows users to browse categories, view products, and add products to the cart. It integrates with mock APIs to fetch data and stores it locally using Room. 

## Technologies Used
- **KTOR**: API calls
- **KOIN**: Dependency injection
- **Room**: Local database storage
- **Material 3**: UI components
- **Jetpack Compose**: UI development
- **MVI**: Architectural pattern

## Features
1. **Search Products**  
   - Search for products by name.
   - Filters products based on the entered text.

2. **Order Management**  
   - Add products to cart directly from the product list.
   - View total price and quantity of products in the cart.
   - Clear cart when pressing "View Order".

3. **API Integration**  
   - Fetch categories and products from a mock API (Mockaroo).
   - Store data locally using Room.

4. **UI Screens**  
   - **Categories and Products Screens**: List categories and products with details.
   - **Navigation Bar**: Bottom navigation for Orders, Menu, and Settings screens.

5. **Responsive Design**  
   - Optimized for portrait mode with UI adjustments for different screen sizes.
