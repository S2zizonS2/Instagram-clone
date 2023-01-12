import { css } from "@emotion/react";

const inputStyle = css`
    .animation-placeholder-input {
        span {
            color: var(--grey-400);
            margin-left: 0.5rem;
        }
        input {
            height: 2.5rem;
            border: 1px solid var(--grey-200);
            background-color: var(--grey-50);
            padding-left: 0.5rem;
            height: 2.3rem;
        }

        input:focus {
            border: 1px solid var(--grey-300);
        }
    }
`;

export default inputStyle;