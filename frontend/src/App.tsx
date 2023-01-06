import { css } from "@emotion/react";
import React from "react";
import Aside from "./components/Navigator/Aside";
import GlobalStyle from "./GlobalStyle";

function App() {
    return (
        <React.Fragment>
            <GlobalStyle />
            <div css={style}>
                <Aside
                    className="aside"
                />
                <div className="aside-mock"></div>

                <div className="content">
                    content
                </div>
            </div>
        </React.Fragment>

    );
}


const style = css`
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
        .aside{
            width: 250px;
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