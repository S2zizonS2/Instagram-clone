import { css, SerializedStyles } from "@emotion/react";
import React from "react";

function MainWrapper(props: { children: React.ReactNode; style?: SerializedStyles }) {
    return (
        <main
            css={[style, props?.style]}
        >
            {props.children}
        </main>
    );
}

const style = css`
    margin-top: 1rem;
    min-width: 350px;
    max-width: 350px;
    border: 1px solid var(--grey-200);
    background-color: white;
    display: flex;
    flex-direction: column;
    align-items: center;
    // 모바일   
    @media screen and (max-width: 768px) {
        border: none;
        background-color: unset;
    }
`;


export default MainWrapper;