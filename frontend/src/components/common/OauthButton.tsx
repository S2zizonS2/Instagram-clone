import styled from "@emotion/styled";
import React from "react";

interface OauthButtonProps {
    icon: any;
    text: string;
    backgroundColor: string;
    color: string;
    onClick?: () => void;
}

/**
 * OauthButton 틀 
 * @param props.icon - icon
 * @param props.text - text
 * @param props.backgroundColor - background-color
 * @param props.color - font-color
 * @param props.color - 버튼 클릭 이벤트
 * @returns 
 */
function OauthButton(props: OauthButtonProps) {
    return (
        <Button
            onClick={props.onClick}
            backgroundColor={props.backgroundColor}
            color={props.color}
        >
            <span>{props.icon}</span>
            <span className="text">{props.text}</span>
        </Button>
    );
}

const Button = styled.button<{
    backgroundColor: string;
    color: string;
}>`
    cursor: pointer;
    border-radius: 8px;
    background-color: ${(props) => props.backgroundColor};
    color: ${(props) => props.color};
    width: 100%;
    height: 34px;
    display: inline-flex;
    justify-content: center;
    align-items: center;

    .text {
        font-size: 0.8rem;
        font-weight: 600;
    }

    svg {
        font-size: 1.3rem;
        margin-right: 0.3rem;
    }
`;

export default OauthButton;