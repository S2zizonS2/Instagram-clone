import React from "react";
import WordLogo from "@/assets/DLogo.svg";
import { css } from "@emotion/react";
/**
 * /acccounts/* 에서 사용되는 로고
 * @returns 
 */
function Logo() {
    return (
        <figure css={style}>
            <img src={WordLogo} />
        </figure>
    );
}

const style = css`
    cursor: pointer;
    width: 12rem;
    height: auto;
`;

export default Logo;