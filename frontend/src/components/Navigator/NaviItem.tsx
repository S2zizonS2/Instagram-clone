import { css } from "@emotion/react";
import React from "react";

interface NaviItemProps {
    icon: any;
    text: string;
}


function NaviItem(props: NaviItemProps) {
    return (
        <li css={style}>
            {props.icon}
            <p>{props.text}</p>
        </li>
    );
}

const style = css`
    display: inline-flex;
    align-items: center;
    cursor: pointer;
    width: 100%;
    height: 100%;
    svg {
        font-size: 2rem;
        margin-right: 0.5rem;
    }
    @media screen and (max-width: 1239px){
        p {
            display: none;
        }
    }

    @media screen and (min-width: 769px) {
        min-height: 48px;
        width: 100%;
        margin-bottom: 0.5rem;
    }

    @media screen and (max-width: 768px){
        justify-content: center;
        align-items: center;
    }
`;

export default NaviItem;