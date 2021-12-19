import React, {Component} from 'react';
import SockJsClient from 'react-stomp';
import './App.css';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import './css/MessageStyle.css';
import NameComponent from "./components/NameComponent";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
             clientMessage : 0
        }
      }
    
      sendMessage = (msg) => {
        this.clientRef.sendMessage('/topic/message', msg);
      }
    
      render() {
        return (
          <div>
            <SockJsClient url='http://localhost:8080/ws-message' topics={['/topic/message']}
                onMessage={(msg) => { 
                   
                    this.setState({ 
                        clientMessage: this.state.clientMessage +1
                      })

                    console.log(msg.message); }}
                ref={ (client) => { this.clientRef = client }} />
        
                  <p>{this.state.clientMessage}</p>
                
            </div>


        );
      }
}

export default App;