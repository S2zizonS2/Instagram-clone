import { css } from "@emotion/react";
import React from "react";
import Navigator from "./components/Navigator/Navigator";
import GlobalStyle from "./GlobalStyle";

function App() {
    return (
        <React.Fragment>
            <GlobalStyle />
            <div css={style}>
                <Navigator
                    className="navi"
                />
                <div className="content">
                    content
                </div>
            </div>
        </React.Fragment>

    );
}


const style = css`
    display: flex;
    width: 100vw;
    height: 200vh;
    flex-direction: row;
    .content {
        width: 100%;
        height: 100%;
        background: red;
    }
    .navi {
        width: 100%;
        height: 48px;
    }

    

    @media screen and (max-width: 768px){
        .navi {
            position: fixed;
            bottom: 0;
            flex-direction: column;
            background-color: yellow;
        }
    }
    
`;
export default App;