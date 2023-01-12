import { css } from "@emotion/react";

const WrapStyle = css`
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

export default WrapStyle;