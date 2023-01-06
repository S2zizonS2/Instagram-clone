import { css } from "@emotion/react";
import React from "react";
import Content from "./components/Content/Content";

import Aside from "./components/Navigator/Aside";
import Theme from "./components/Theme/Theme";
import GlobalStyle from "./GlobalStyle";
import ThemeContext from "./store/ThemeContext";

function App() {

    return (
        <React.Fragment>
            <Theme>
                <GlobalStyle />
                <div css={style}>
                    <Aside
                        className="aside"
                    />
                    <div className="aside-mock"></div>

                    <Content
                        className="content"
                    />
                </div>
            </Theme>

        </React.Fragment >

    );
}


const style = css`
    button {
        border: 1px solid;
        cursor: pointer;
    }
    display: flex;
    height: 200vh;
    flex-direction: row;
    .content, .aside-mock {
        width: 100%;
        height: 100%;
    }

    .aside {
        position: fixed;
    }

    @media screen and (min-width: 1239px) {
        .aside, .aside-mock{
            max-width: 250px;
        }   
        
    }

    @media screen and (min-width: 769px) and (max-width: 1238px) {
        .aside{
            left: 0;
            
        }

        .aside, .aside-mock {
            min-width: 4.5rem;
            max-width: 4.5rem;
        }
    }

    @media screen and (max-width: 768px){
        flex-direction: column-reverse;
        .aside {
            bottom: 0;
            height: 52px;
            max-height: 52px;
        }
    }
    
`;
export default App;