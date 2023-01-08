import { css } from "@emotion/react";
import React from "react";
import { Route, Routes } from "react-router-dom";
import EmailSignup from "./EmailSignup/EmailSignup";


/**
 * /accounts/* 에서 렌더링되는 페이지
 * @returns 
 */
function index() {
    return (
        <section css={style}>
            <Routes>
                <Route path="emailsignup" element={<EmailSignup />} />
            </Routes>
        </section>
    );
}

const style = css`
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    /* align-items: center; */

    background-color: var(--grey-100);
`;


export default index;