import {createStore, applyMiddleware,compose} from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const intialState = {};
const middleware = [thunk];
let store ;
let divTool = window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__();
if(divTool == null){
  divTool = "";
}
store = createStore(rootReducer,  intialState,
        compose(applyMiddleware(...middleware),divTool));


export default store;